import React from 'react';
import {Route, Routes} from "react-router-dom";
import AuthComponent from "../components/AuthComponent";
import HomeComponent from "../components/HomeComponent";
import PageNotFound from "../components/PageNotFound";
import ProductPageComponent from "../components/ProductPageComponent";
import RegistrationComponent from "../components/RegistrationComponent";
import BasketComponent from "../components/BasketComponent";
import RequireAuth from "../components/RequireAuth";
import PersonalPageComponent from "../components/PersonalPageComponent";


const AppRoutes = () => {
    return (
        <Routes>
            <Route exact path="/auth/login" element={<AuthComponent/>}/>
            <Route exact path="/auth/registration" element={<RegistrationComponent/>}/>
            <Route exact path="/" element={<HomeComponent/>}/>
            <Route path="*" element={<PageNotFound/>}/>
            <Route exact path="/product/healthGlasses" element={<ProductPageComponent/>}/>
            <Route exact path="/product/sunglasses" element={<ProductPageComponent/>}/>
            <Route exact path="/product/contactLenses" element={<ProductPageComponent/>}/>
            <Route element={<RequireAuth/>}>
                <Route exact path="/cartProducts" element={<BasketComponent/>}/>
                <Route exact path="/personalInfo" element={<PersonalPageComponent/>}/>
            </Route>
        </Routes>
    );
};

export default AppRoutes;