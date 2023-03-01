using Microsoft.AspNetCore.Mvc;
using PokerFace.Commands.Session;
using PokerFace.Data.Entities;
using PokerFace.Web.Controllers;

namespace PokerFace.Controllers
{
    public class SessionController : ApiController
    {
        [HttpPost]
        public async Task<ActionResult> CreateSession([FromBody] CreateSessionCommand command) => await SendMessage(command);

        [HttpGet("{id}")]
        public async Task<ActionResult<Session>> GetSession([FromRoute]CreateSessionCommand command) => await SendMessage(command); 
        
        [HttpGet]
        public async Task<ActionResult<Session>> GetSessionUsers([FromQuery]GetSessionUsersCommand command) => await SendMessage(command);

        [HttpGet]
        public async Task<ActionResult<Session>> LogoutSession([FromQuery]LogoutSessionCommand command) => await SendMessage(command);
    }
}
