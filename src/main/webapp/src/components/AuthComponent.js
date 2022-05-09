import React, { useEffect, useState } from 'react';
import {Link, Navigate} from 'react-router-dom';
import styles from '../resourses/css/login.module.scss';
import AuthService from "../services/AuthService";
import { useNavigate } from 'react-router-dom';

const baseURL = "http://localhost:8080/user?email=newuser@mail.ru";

export default function AuthComponent(props){

    let [user, setUser] = useState({username: '', password: ''})

    const handleChange = (event) => {
        const {name, value} = event.target;
        setUser(prevState => (
            {
            ...prevState,
                [name]: value
        }))
    }

    let navigate = useNavigate();
    const handleSubmit = (e) => {
        AuthService.login(user.username, user.password).then(
            () => {
                navigate("/");
                window.location.reload();
            }
        )

    }

    return (
        <div className={styles.megawrapp}>
            <div className={styles.wrapp + " " + styles.fadeInDown}>
                <div className={styles.formContent}>

                    <Link to='/auth/login'><h2 className={styles.active}> Войти </h2></Link>
                    <Link to='/auth/registration'><h2 className={styles.inactive + " " + styles.underlineHover}>Зарегистрироваться </h2></Link>

                    <div className='f'>
                        <input type="text" name="username" value={user.username} onChange={handleChange} placeholder="Логин"/>
                        <input type="password" name="password" value={user.password} onChange={handleChange} placeholder="Пароль"/>
                        <button className={styles.loginBtn} onClick={handleSubmit}>Войти</button>
                    </div>

                </div>
            </div>
        </div>
    )

}