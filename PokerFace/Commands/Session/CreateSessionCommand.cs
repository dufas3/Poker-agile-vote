using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    public class CreateSessionCommand : IRequest
    {
        public int Id { get; set; }
        public int ModeratorId { get; set; }
    }
    public class CreateSessionCommandHandler : IRequestHandler<CreateSessionCommand>
    {
        private readonly ISessionRepository sessionRepository;
        private readonly IUserRepository userRepository;

        public CreateSessionCommandHandler(ISessionRepository sessionRepository, IUserRepository userRepository)
        {
            this.sessionRepository = sessionRepository;
            this.userRepository = userRepository;
        }

        public async Task<Unit> Handle(CreateSessionCommand request, CancellationToken cancellationToken)
        {
            Data.Entities.Session session = new Data.Entities.Session();
            var moderator = await userRepository.GetAsync(request.ModeratorId);

            if (moderator == null)
                throw new BadHttpRequestException("No moderator found");

            if (moderator.RoomId != null)
                throw new BadHttpRequestException("This moderator already has active session");

            session.RoomId = request.Id;
            session.ModeratorId = request.ModeratorId;
            moderator.RoomId = session.RoomId;

            await sessionRepository.AddAsync(session);
            await userRepository.UpdateAsync(moderator);

            return Unit.Value;
        }
    }
}
