import React, {Component} from 'react';
import axios from "axios";

const API_URL = "/api/auth/";

class AuthService {


    login(username, password) {
        return axios
            .post(API_URL + "signin", {
                username,
                password
            })
            .then(response => {
                if (response.data.token) localStorage.setItem("user", JSON.stringify(response.data));
                return response.data;
            });
    }

    register(user) {
        return axios
            .post(API_URL + "registration" ,{
                email: user.email,
                username: user.username,
                password: user.password
        }).then(resp => {
            return resp.data;
            })
    }

    logout() {
        localStorage.removeItem("user");
    }

    getSessionUser() {
        return JSON.parse(localStorage.getItem('user'));
    }

    authHeader() {
        const user = this.getSessionUser();

        if (user && user.token) {
            return { Authorization: 'Bearer ' + user.token  };
        } else {
            return {};
        }
    }

}

export default new AuthService();