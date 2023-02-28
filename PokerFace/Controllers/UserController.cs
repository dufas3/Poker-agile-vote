using Microsoft.AspNetCore.Mvc;
using PokerFace.Commands.User;
using PokerFace.Web.Controllers;

namespace PokerFace.Controllers
{
    public class UserController : ApiController
    {
        [HttpGet]
        public async Task<ActionResult> GetModerator([FromQuery] GetModeratorCommand command) => await SendMessage(command);
        [HttpGet]
        public async Task<ActionResult> GetAll([FromRoute]GetUsersCommand command) => await SendMessage(command);
        [HttpPost]
        public async Task<ActionResult> AddToSession([FromBody] AddToSessionCommand command) => await SendMessage(command);

    }
}
