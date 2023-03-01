const SetActiveCards = async () => {
  if (!props) return;

  const requestOptions = {
    method: "Post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      roomId: props.roomId,
      cards: props.cards,
    }),
  };

  const url = new URL("https://localhost:5001/api/card/SetActiveCards");
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
