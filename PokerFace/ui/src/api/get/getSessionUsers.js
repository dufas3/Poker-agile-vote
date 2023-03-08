import ConnectionUrl from "../../common/connectionUrl";

const GetSessionUsers = async ({id}) => {

    const requestOptions = {
        method: "Get",
        headers: {"Content-Type": "application/json"},
    };

    if (!id) return;

    const url = ConnectionUrl({appendix: "/session/getSessionUsers"});
    url.searchParams.append("id", id);

    try {
        const response = await fetch(url.toString(), requestOptions);
        const isJson = response.headers
            .get("content-type")
            ?.includes("application/json");
        const data = isJson && (await response.json());
        return data;
    } catch (error) {
        return;
    }
};
export default GetSessionUsers;
