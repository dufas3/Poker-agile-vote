import ConnectionUrl from "../common/connectionUrl";

const GetSessionUsers = async (props) => {
  const requestOptions = {
    method: "Get",
    headers: { "Content-Type": "application/json" },
  };

  if (!props) return;

  console.log("props", props);

  const url = ConnectionUrl({ appendix: "/session/getSessionUsers" });
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
};
export default GetSessionUsers;
