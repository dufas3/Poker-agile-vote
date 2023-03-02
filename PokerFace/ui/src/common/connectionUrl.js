const ConnectionUrl = (props) => {
    return new URL("https://localhost:5001/api" + props.appendix);
};

export default ConnectionUrl;
