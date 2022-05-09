import React from 'react';
import AuthService from "../services/AuthService";
import {Navigate, Outlet} from "react-router";

const RequireAuth = () => {

    let TOKEN = AuthService.getSessionUser()


    return (
        <div>
            {
                (TOKEN === null) ? <Navigate to="/auth/login"/>: <Outlet />
            }
        </div>
    );
};

export default RequireAuth;