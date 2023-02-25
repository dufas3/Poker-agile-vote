import {useState} from "react";

const getModerator = (props) => {

    const [responseData, setResponseData] = useState();

    const requestOptions = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({userEmail: props.email, userPassword: props.password})
    };

    fetch('http://localhost/api/user/getmoderator', requestOptions)
        .then(async response => {
            const isJson = response.headers.get('content-type')?.includes('application/json');
            const data = isJson && await response.json();
            setResponseData(data);

        })
    return responseData;
}
export default getModerator;