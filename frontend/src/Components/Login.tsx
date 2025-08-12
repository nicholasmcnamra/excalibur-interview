import { Box, Button, Grid, IconButton, TextField } from "@mui/material"
import { useEffect, useRef, useState } from "react";
import { ClassList } from "./ClassList";

export const Login:React.FC = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loginError, setLoginError] = useState<string | null>(null);
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
                console.log(username);
    console.log(password);
            setClassData(await response.json());
            console.log(classData);
        } catch (error) {
            console.log("Error fetching response", error);
            setLoginError("Login failed, please try again.");
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
            
        </Grid>
    )
}