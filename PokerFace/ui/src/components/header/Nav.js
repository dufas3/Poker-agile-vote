import FestoLogo from '../../imgs/festo.png';
const Nav = () => {
    return (
        <header>
            <nav class="navbar navbar-expand-sm navbar-light bg-light border-bottom box-shadow mb-3 fixed-top">
                <div class="container-fluid">
                    <a class="navbar-brand">Festo Scrum Poker</a>
                    <a><i class="bi bi-person-fill"></i>Login</a>
                    <a class="navbar-brand" href="https://www.festo.com/us/en/" target="_blank">
                        <img src={FestoLogo} className="logo" />
                    </a>
                </div>
            </nav>
        </header>
    )
}
export default Nav;