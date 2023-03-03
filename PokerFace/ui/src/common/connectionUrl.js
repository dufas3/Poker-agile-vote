const ConnectionUrl = (props) => {
  return new URL(
    "https://pokerfaceapp-backend.azurewebsites.net/api" + props.appendix
  );
};

export default ConnectionUrl;
