import {Route, Routes, useNavigate, useSearchParams} from "react-router-dom";
import {useCallback, useState} from "react";

function RouteManager() {
    const [searchParams] = useSearchParams();
    const [navig, setNavig] = useState();
    const navigate = useNavigate();
    const handleNavigate = useCallback(() => navig, [navigate]);

    useState(() => {
        if (searchParams.get("room") == undefined) {
            setNavig(navigate("/Login", {replace: true}));
        } else {
            setNavig(navigate("/Join?room=" + searchParams.get("room"), {replace: true}));
        }
        handleNavigate();
    })

    return (
        <>
        </>
    );
}

export default RouteManager;
