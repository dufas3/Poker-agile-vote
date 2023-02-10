import './Footer.css'

function Footer() {
    return (
        <footer class="text-muted fixed-bottom container-fluid mx-5 px-5 pt-3 text-decoration-none d-flex flex-row">
            <div class="d-flex">
                <div class="mr-auto p-5">© 2021 Festo Corporation. All Rights Reserved</div>
                <div class="p-5 d-flex justify-content-end">
                    <a href="https://www.festo.com/us/en/e/legal/-id_3741/" target="_blank">Imprint</a>
                    <a href="https://www.festo.com/us/en/e/privacy-statement-id_3749/" target="_blank" class="text-decoration-none text-muted">Data privacy</a>
                    <a href="https://www.festo.com/us/en/e/legal/terms-and-conditions-of-sale-id_3747/" target="_blank" class="text-decoration-none text-muted">Terms and Conditions
                        of Sale</a>
                    <a href="https://www.festo.com/us/en/e/cloud-services-id_129924/" target="_blank" class="text-decoration-none text-muted">Cloud Services</a>
                </div>
            </div>
        </footer>
    );
}
export default Footer;