import { Box, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from "@mui/material"

export interface ClassList {
    classes: any[]
    id: number,
    user: string
}

export const ClassList:React.FC<{ classList:ClassList }> = ( { classList } ) => {

    console.log(classList);
    if (!classList) {
        return (
            <Box>
                <Typography>
                    No classes to show.
                </Typography>
            </Box>
        )
    }
    return (
        <TableContainer
            component={Paper}
        >
            <Table
                sx={{
                    minwidth: 650
                }}
            >
                <TableHead>
                    <TableRow>
                        <TableCell sx={{fontWeight: 'bold'}}>Class</TableCell>
                        <TableCell align="right" sx={{fontWeight: 'bold'}}>Score</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {classList?.classes.map((row:any) => (
                        <TableRow
                            key={row.className}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">{row.name}</TableCell>
                            <TableCell align="right">{row.score}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    )
}