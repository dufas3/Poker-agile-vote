import ConnectionUrl from "../../common/connectionUrl";

const SetActiveUserCard = async (props) => {

    if (!props) return;

    const requestOptions = {
        method: "Post",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            userId: props.userId,
            cardId: props.cardId,
        }),
    };

    const url = ConnectionUrl({appendix: "/card/SetActiveUserCard"});
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
export default SetActiveUserCard;