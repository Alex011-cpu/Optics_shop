import React from 'react';
import {Link} from "react-router-dom";
import styles from "../resourses/css/success.module.scss";

const SuccessComponent = () => {
    return (
        <div className={styles.congratulation}>
            <div className={styles.sub_congrats}>
                <p>Поздравляем с покупкой!</p>
                <img width="250px" src={require("../resourses/img/success.png")} alt="yohooo!"/>
                    <Link to="/" className={styles.submit}>Продолжить покупки</Link>
            </div>
        </div>
    );
};

export default SuccessComponent;