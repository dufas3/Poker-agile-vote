import ConnectionUrl from "../common/connectionUrl";

const LogoutUser = async (props) => {

    console.log("Logout", props)

    const requestOptions = {
        method: "Get",
        headers: {"Content-Type": "application/json"},
    };

    if (!props) return;

    const url = ConnectionUrl({appendix: "/session/LogoutUser"});
    url.searchParams.append("roomId", props.roomId);
    url.searchParams.append("userId", props.userId);


    try {
        const response = await fetch(url.toString(), requestOptions);
        const isJson = response.headers
            .get("content-type")
            ?.includes("application/json");
        const data = isJson && (await response.json());
        return data;
    } catch (error) {
        console.log("Caught error", error);
        return;
    }
}
export default LogoutUser