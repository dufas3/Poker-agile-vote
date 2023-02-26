using MediatR;
using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    public class GetSessionUsersCommand : IRequest<List<Data.Entities.User>>
    {
        public int Id { get; set; }
    }

    public class GetSessionUsersCommandHandler : IRequestHandler<GetSessionUsersCommand, List<Data.Entities.User>>
    {
        private readonly ISessionRepository sessionRepository;

        public GetSessionUsersCommandHandler(ISessionRepository sessionRepository)
        {
            this.sessionRepository = sessionRepository;
        }

        public async Task<List<Data.Entities.User>> Handle(GetSessionUsersCommand request, CancellationToken cancellationToken)
        {
            if (sessionRepository.GetAsync(request.Id) != null)
                return await sessionRepository.GetSessionUsersAsync(request.Id);
            return null;
        }
    }
}
