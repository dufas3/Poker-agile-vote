using MediatR;
using PokerFace.Data.Common;
using PokerFace.Data.Entities;

namespace PokerFace.Commands.Session
{
    public class CreateSessionCommand : IRequest<Data.Entities.Session>
    {
        public int Id { get; set; }
        public int ModeratorId { get; set; }
    }
    public class CreateSessionCommandHandler : IRequestHandler<CreateSessionCommand, Data.Entities.Session>
    {
        private readonly ISessionRepository sessionRepository;
        private readonly IUserRepository userRepository;

        public CreateSessionCommandHandler(ISessionRepository sessionRepository, IUserRepository userRepository)
        {
            this.sessionRepository = sessionRepository;
            this.userRepository = userRepository;
        }

        public async Task<Data.Entities.Session> Handle(CreateSessionCommand request, CancellationToken cancellationToken)
        {
            Data.Entities.Session session = new Data.Entities.Session();

            if (await userRepository.GetAsync(request.ModeratorId) == null)
                return null;

            session.Id = request.Id;
            session.ModeratorId = request.ModeratorId;
            await sessionRepository.AddAsync(session);

            return session;
        }
    }
}
