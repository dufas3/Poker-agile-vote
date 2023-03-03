import ConnectionUrl from "../../common/connectionUrl";

const GetUserSelectedCard = async (props) => {

    const requestOptions = {
        method: "Get",
        headers: {"Content-Type": "application/json"},
    };

    if (!props) return;

    const url = ConnectionUrl({appendix: "/user/getSelectedCard"});
    url.searchParams.append("id", props.id);

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
export default GetUserSelectedCard