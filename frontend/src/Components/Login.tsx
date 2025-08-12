import { Alert, Box, Button, Grid, IconButton, Snackbar, TextField } from "@mui/material"
import { useEffect, useRef, useState } from "react";
import { ClassList } from "./ClassList";

export const Login:React.FC = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loginError, setLoginError] = useState<string | null>(null);
    const [showSnackBarError, setShowSnackBarError] = useState<boolean>(false);
    const [classData, setClassData] = useState<ClassList | null>(null);


    const handleSubmit = async (event:any) => {
        event.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ 
                    username: username, 
                    password: password
                })
            })

            if (!response.ok) {
                setLoginError("Login failed, please try again.")
                setShowSnackBarError(true);
                setClassData(null);
            }

            const data = await response.json();

            if (!data?.classes || data.classes.length === 0) {
                setLoginError("No classes found for this user.");
                setShowSnackBarError(true);
                setClassData(null);
                return;
            }

            setClassData(data);
            setLoginError(null);

        } catch (error) {
            console.log("Error fetching response", error);
            setLoginError("Login failed, please try again.");
            setShowSnackBarError(true);
            setClassData(null);
        }
    }

    const handleLogout = () => {
        setClassData(null);
        setLoginError(null);

        if (username) setUsername("");
        if (password) setPassword("");
    }
    return (
        <Grid
            component="form"
            onSubmit={handleSubmit}
            container
            spacing={2}
            alignItems="center"
            justifyContent="center"
            sx={{
                minHeight: "100vh",
                px: 2,
            }}
        >
{!classData ?  (           
            <Grid
                sx={{
                    maxWidth: 500,
                    width: '100%',
                }}
            >
                <TextField
                    id="username"
                    className="login-form"
                    label='username'
                    onChange={e => setUsername(e.target.value)}
                    fullWidth
                    margin="normal"
                />
                <TextField
                    id="password"
                    className="login-form"
                    label='password'
                    onChange={e => setPassword(e.target.value)}
                    type="password"
                    fullWidth
                    margin="normal"
                />
                <Button 
                    type="submit" 
                    aria-label="search"
                    variant="contained"
                    sx={{
                        px: 4,
                        py: 2,
                        borderRadius: 2,
                    }}
                    disabled={ !username || !password }
                >
                    Login
            </Button>
            </Grid>
            )
            : (
            <Box
                sx={{
                    p:2,
                }}
            >
                <ClassList classList={classData}></ClassList>
                <Button
                    variant="contained"
                    onClick={handleLogout}
                    sx={{
                        mt:2,
                    }}
                >
                    Logout
                </Button>
            </Box>
            )
        }
        <Snackbar
            open={showSnackBarError}
            autoHideDuration={4000}
            onClose={() => setShowSnackBarError(false)}
            anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
        >
            <Alert
                onClose={() => setShowSnackBarError(false)}
                severity='error'
                sx={{ width: '100%' }}
            >
                {loginError}
            </Alert>
        </Snackbar>
            
        </Grid>
    )
}