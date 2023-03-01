const LogoutSession = async (props) => {
  if (!props) return;

  const requestOptions = {
    method: "Get",
    headers: { "Content-Type": "application/json" },
  };

  const url = new URL("https://localhost:5001/api/session/logoutsesion");
  url.searchParams.append("roomId", props.roomId);

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

export default LogoutSession;
