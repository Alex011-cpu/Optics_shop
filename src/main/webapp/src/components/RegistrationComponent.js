import React, {useState } from 'react';
import {Link} from 'react-router-dom';
import styles from '../resourses/css/login.module.scss';
import { useNavigate } from 'react-router-dom';
import AuthService from "../services/AuthService";

const RegistrationComponent = () => {

    let [user, setUser] = useState({email: '',username: '',password: '', confirmPassword: ''})

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
        AuthService.register(user).then(
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

                    <Link to='/auth/login'><h2 className={styles.inactive + " " + styles.underlineHover}> Войти </h2></Link>
                    <Link to='/auth/registration'><h2 className={styles.active}>Зарегистрироваться </h2></Link>

                    <div className="f">
                        <div className={styles.error_module}>
                            <span></span>
                        </div>
                        <input type="text" name="email" value={user.email} onChange={handleChange} placeholder="Почта"/>
                        <input type="text" name="username" value={user.username} onChange={handleChange} placeholder="Логин"/>
                        <input type="password" name="password" value={user.password} onChange={handleChange} placeholder="Пароль"/>
                        <input type="password" name="confirmPassword" value={user.confirmPassword} onChange={handleChange} placeholder="Подтвердить пароль" />
                        <button className={styles.loginBtn} onClick={handleSubmit}>Войти</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegistrationComponent;