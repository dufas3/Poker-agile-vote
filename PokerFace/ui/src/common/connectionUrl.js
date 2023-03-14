const ConnectionUrl = ({ appendix }) => {
  return new URL(
    "https://pokerface-testbackend.azurewebsites.net/api" + appendix
  );
};

export default ConnectionUrl;
