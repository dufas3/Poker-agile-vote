using PokerFace.Data.Common;

namespace PokerFace.Commands.Session
{
    public class SessionDto
    {
        public string RoomId { get; set; }
        public List<int> UserIds { get; } = new List<int>();
        public List<int> CardIds { get; } = new List<int>();
        public SessionState State { get; set; }
    }
}
