import ConnectionUrl from "../../common/connectionUrl";

const GetUser = async ({ userId }) => {
  if (!userId) return;

  const requestOptions = {
    method: "Get",
    headers: { "Content-Type": "application/json" },
  };

  const url = ConnectionUrl({ appendix: "/user/getUser" });

  url.searchParams.append("id", userId);

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

export default GetUser;
