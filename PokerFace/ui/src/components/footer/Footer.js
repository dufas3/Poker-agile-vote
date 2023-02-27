function Footer() {
    return (<div className="container">
            <footer className="d-flex flex-wrap justify-content-between align-items-center py-2 my-3 border-top"><p
                className="col-md-5 mb-0 text-muted">© 2021 Festo Corporation. All Rights Reserved</p>
                <ul className="nav col-md-5 justify-content-end">
                    <li><a href="https://www.festo.com/us/en/e/legal/-id_3741/" target="_blank"
                           className="nav-link px-2 text-muted" id="F1">Imprint</a></li>
                    <li><a href="https://www.festo.com/us/en/e/privacy-statement-id_3749/" target="_blank"
                           className="nav-link px-2 text-muted" id="F2">Data privacy</a></li>
                    <li><a href="https://www.festo.com/us/en/e/legal/terms-and-conditions-of-sale-id_3747/"
                           target="_blank" className="nav-link px-2 text-muted" id="F3">Terms and Conditions
                        of Sale</a></li>
                    <li><a href="https://www.festo.com/us/en/e/cloud-services-id_129924/" target="_blank"
                           className="nav-link px-2 text-muted" id="F4">Cloud Services</a></li>
                </ul>
            </footer>
        </div>
    );
}

export default Footer;