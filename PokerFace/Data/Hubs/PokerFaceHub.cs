using Microsoft.AspNetCore.SignalR;
using PokerFace.Data.Common;

namespace PokerFace.Data.Hubs
{
    public class PokerFaceHub : Hub
    {
        private readonly IUserRepository userRepository;
        private readonly ISignalRService signalRService;

        public PokerFaceHub(IUserRepository userRepository, ISignalRService signalRService)
        {
            this.userRepository = userRepository;
            this.signalRService = signalRService;
        }

        [HubMethodName("ReceiveConnectSockets")]
        public async Task ReceiveConnectSockets(string message)
        {
            //message = userId
            try
            {
                if (!string.IsNullOrEmpty(message))
                    await userRepository.SetSocketId(Context.ConnectionId, int.Parse(message));
                else
                    await userRepository.SetSocketId(Context.ConnectionId, 0);
            }
            catch
            {
                throw new BadHttpRequestException("");
            }
        }

        public override async Task OnDisconnectedAsync(Exception exception)
        {
            var user = await userRepository.GetAsync(Context.ConnectionId);
            if (!user.Name.Contains("@"))
            {
                await userRepository.DeleteAsync(user);
                await signalRService.SendMessage(StaticHubMethodNames.SendPlayerListUpdate, user.RoomId);
            }
            //else handle moderator log out
        }

        [HubMethodName("PlayerListUpdate")]
        public async Task SendPlayerListUpdate(string socketId)
        {
            if (Context.ConnectionId == socketId)
                await Clients.Client(socketId).SendAsync("PlayerListUpdate");
        }

        [HubMethodName("UserCardSelectionUpdate")]
        public async Task SendUserCardSelectionUpdate(string socketId)
        {
            if (Context.ConnectionId == socketId)
                await Clients.Client(socketId).SendAsync("UserCardSelectionUpdate");
        }

        [HubMethodName("ActiveCardsUpdate")]
        public async Task SendActiveCardsUpdate(string socketId)
        {
            if (Context.ConnectionId == socketId)
                await Clients.Client(socketId).SendAsync("ActiveCardsUpdate");
        }

        [HubMethodName("SessionStateUpdate")]
        public async Task SendSessionStateUpdate(string socketId)
        {
            if (Context.ConnectionId == socketId)
                await Clients.Client(socketId).SendAsync("SessionStateUpdate");
        }
    }
}
