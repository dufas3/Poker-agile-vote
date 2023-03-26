using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using PokerFace.Commands.Settings;
using PokerFace.Web.Controllers;

namespace PokerFace.Controllers
{
   
    public class SettingsController : ApiController
    {
        [HttpGet]
        public async Task<ActionResult> GetSettings([FromQuery] GetSettingsCommand command) => await SendMessage(command);
    }
}
