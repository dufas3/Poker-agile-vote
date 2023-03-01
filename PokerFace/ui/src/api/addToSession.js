import ConnectionUrl from "../common/connectionUrl";

const AddToSession = async (props) => {
  const requestOptions = {
    method: "Post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      name: props.name,
      roomId: props.roomId,
    }),
  };

  if (!props) return;

  console.log("props", props);

  const url = ConnectionUrl({ appendix: "/user/addToSession" });

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
};
export default AddToSession;
