import {ToastContainer, toast} from 'react-toastify'
import {useEffect, useState} from "react";

const Notifications = (props) => {
    const [firstLoad, setFirstLoad] = useState(0)
    let message = "";

    const notify = (props) =>
        toast.info(props.message, {
            position: "top-center",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",
        });
    useEffect(() => {
        if (firstLoad >= 1) {
            if(props.data.state == 0){
                message = "Votes are cleared by the moderator. Voting re-started"
            } else if(props.data.state == 1){
                message = "Moderator flipped the cards. Voting stopped."
            } else if(props.data.state == 2){
                message = "Voting finished."
            }
            notify({message: message})
        } else {
            setFirstLoad(firstLoad + 1)
        }
    }, [props.data.state])
    return (
        <>
        <ToastContainer/>
        </>
    )
}
export default Notifications;