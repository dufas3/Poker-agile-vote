using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.User
{
    public class AddToSessionCommand : IRequest
    {
        public int UserId { get; set; }
        public int SessionId { get; set; }
    }

    public class AddToSessionCommandHandler : IRequestHandler<AddToSessionCommand>
    {
        ISessionRepository sessionRepository { get; set; }
        IUserRepository userRepository { get; set; }

        public AddToSessionCommandHandler(ISessionRepository sessionRepository, IUserRepository userRepository)
        {
            this.userRepository = userRepository;
            this.sessionRepository = sessionRepository;
        }
        public async Task<Unit> Handle(AddToSessionCommand request, CancellationToken cancellationToken)
        {
            //fix
            var session = await sessionRepository.GetAsync(request.SessionId);
            if (session == null)
                throw new BadHttpRequestException("Session not found!");

            return Unit.Value;
        }
    }
}
