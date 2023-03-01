const ConnectionUrl = (props) => {
  let url = new URL("");
  props
    ? (url = new URL("pokerfaceapp-17528.azurewebsites.net/api"))
    : (url = new URL(
        "pokerfaceapp-17528.azurewebsites.net/api" + props.appendix
      ));
};

export default ConnectionUrl;
