namespace PokerFace.Data.Common
{
    public interface ISessionService
    {
        public Task CreateSession(int moderatorId);
    }
}
