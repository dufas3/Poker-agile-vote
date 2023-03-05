import ConnectionUrl from "../../common/connectionUrl";

const SetActiveCards = async (props) => {

    if (!props) return;

    const requestOptions = {
        method: "Post",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            roomId: props.roomId,
            cardIds: props.cards,
        }),
    };

    const url = ConnectionUrl({appendix: "/session/SetActiveCards"});
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
export default SetActiveCards;
