using Microsoft.AspNetCore.SignalR;
using PokerFace.Data.Common;

namespace PokerFace.Data.Hubs
{
    public class PokerFaceHub : Hub
    {
        private readonly IUserRepository userRepository;

        public PokerFaceHub(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
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
