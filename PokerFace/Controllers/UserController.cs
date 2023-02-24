using Microsoft.AspNetCore.Mvc;
using PokerFace.Commands.User;
using PokerFace.Web.Controllers;

namespace PokerFace.Controllers
{
    public class UserController : ApiController
    {
        [HttpPost]
        public async Task<ActionResult> CheckModerator([FromBody] CheckModeratorCommand command) => await SendMessage(command);
        [HttpGet]
        public async Task<ActionResult> GetAll([FromRoute]GetUsersCommand command) => await SendMessage(command);

    }
}
