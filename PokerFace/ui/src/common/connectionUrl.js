const ConnectionUrl = (props) => {
  return new URL(
    "https://pokerfaceapp-backend.azurewebsites.net" + props.appendix
  );
};

export default ConnectionUrl;
