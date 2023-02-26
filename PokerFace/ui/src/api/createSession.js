const CreateSession = async (props) => {
  if (!props) return;
  console.log("create session props", props);
  const requestOptions = {
    method: "Post",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      id: props.id,
      moderatorId: props.moderatorId,
    }),
  };

  const url = new URL("https://localhost:5001/api/session/createSession");

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

export default CreateSession;
