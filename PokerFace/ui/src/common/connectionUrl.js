const ConnectionUrl = ({appendix}) => {
    return new URL(
        "https://pokerfaceapp-backend.azurewebsites.net/api" + appendix
    );
};

export default ConnectionUrl;
