namespace PokerFace.Data.Common
{
    public interface ISignalRService
    {
        Task SendMessage(string methodName, int roomId);
    }
}
