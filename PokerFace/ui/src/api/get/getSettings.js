import ConnectionUrl from "../../common/connectionUrl";

const GetSettings = async ({roomId}) => {

    const requestOptions = {
        method: "Get",
        headers: {"Content-Type": "application/json"},
    };

    const url = ConnectionUrl({appendix: "/settings/getSettings"});
    url.searchParams.append("roomId", roomId);

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
};

export default GetSettings;