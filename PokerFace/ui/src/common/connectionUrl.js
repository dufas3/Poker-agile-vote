const ConnectionUrl = ({appendix}) => {
    return new URL(
        "https://localhost:5001/api" + appendix
    );
};

export default ConnectionUrl;
