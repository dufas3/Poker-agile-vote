import ConnectionUrl from "../common/connectionUrl";

const SetSessionState = async (props) =>{
    if (!props) return;

    const requestOptions = {
        method: "Post",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            roomId: props.roomId,
            state: props.state,
        }),
    };

    const url = ConnectionUrl({ appendix: "/session/SetSessionState" });
    try {
        const response = await fetch(url.toString(), requestOptions);
        const isJson = response.headers
            .get("content-type")
            ?.includes("application/json");
        const data = isJson && (await response.json());
        return data;
    } catch (error) {
        console.log("Caught error");
        return;
    }
}
export default SetSessionState;