using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.User
{
    //moderators log's out or inactivity period reaches, session is deleted
    public class LogoutUserCommand : IRequest
    {
        public int UserId { get; set; }
        public int RoomId { get; set; }
    }

    public class LogoutUserCommandHandler : IRequestHandler<LogoutUserCommand>
    {
        private readonly IUserRepository userRepository;

        public LogoutUserCommandHandler(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        public async Task<Unit> Handle(LogoutUserCommand request, CancellationToken cancellationToken)
        {
            await userRepository.LogoutUserAsync(request.UserId, request.RoomId);
            return Unit.Value;
        }
    }
}
