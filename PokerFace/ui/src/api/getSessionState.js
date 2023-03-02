import ConnectionUrl from "../common/connectionUrl";

const GetSessionState = async (props) => {
  if (!props) return;

  const requestOptions = {
    method: "Get",
    headers: { "Content-Type": "application/json" },
  };

  const url = ConnectionUrl({ appendix: "/session/GetSessionState" });

  url.searchParams.append("roomId", props.roomId);

  try {
    const response = await fetch(url.toString(), requestOptions);

    const isJson = response.headers
      .get("content-type")
      ?.includes("application/json");
    const data = isJson && (await response.json());
    console.log("getSessionState: ", data);
    return data;
  } catch (error) {
    console.log("Caught error");
    return;
  }
};

export default GetSessionState;
