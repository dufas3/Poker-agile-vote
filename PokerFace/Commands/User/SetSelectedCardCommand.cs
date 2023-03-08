using MediatR;
using PokerFace.Data.Common;
using PokerFace.Data.Hubs;
namespace PokerFace.Commands.User
{
    public class SetSelectedCardCommand : IRequest
    {
        public int UserId { get; set; }
        public int CardId { get; set; }
    }

    public class SetSelectedCardCommandHandler : IRequestHandler<SetSelectedCardCommand>
    {
        private readonly IUserRepository userRepository;
        private readonly ISignalRService signalRService;

        public SetSelectedCardCommandHandler(IUserRepository userRepository, ISignalRService signalRService)
        {
            this.userRepository = userRepository;
            this.signalRService = signalRService;
        }

        public async Task<Unit> Handle(SetSelectedCardCommand request, CancellationToken cancellationToken)
        {
            await userRepository.SetSelectedCardAsync(request.UserId, request.CardId);

            var roomId = await userRepository.GetRoomId(request.UserId);
            await signalRService.SendMessage(StaticHubMethodNames.SendPlayerListUpdate, roomId);

            return Unit.Value;
        }
    }
}
