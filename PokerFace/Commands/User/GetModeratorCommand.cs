using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.User
{
    public class GetModeratorCommand : IRequest<ModeratorDto>
    {
        public string UserEmail { get; set; }
        public string UserPassword { get; set; }
    }

    public class GetModeratorCommandHandler : IRequestHandler<GetModeratorCommand, ModeratorDto>
    {
        private readonly IUserRepository userRepository;
        private readonly ISessionService sessionService;
        public GetModeratorCommandHandler(IUserRepository userRepository, ISessionService sessionService)
        {
            this.userRepository = userRepository;
            this.sessionService = sessionService;
        }
        public async Task<ModeratorDto> Handle(GetModeratorCommand request, CancellationToken cancellationToken)
        {
            //create new session if in session table doesnt exist moderator id, otherwise do nothign
            var user = await userRepository.GetModerator(request.UserEmail, request.UserPassword);

            if (user.RoomId == null)
                await sessionService.CreateSession(user.Id);

            return user.ToModeratorDto();
        }
    }
}
