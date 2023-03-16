using PokerFace.Data.Entities;

namespace PokerFace.Commands.Email
{
    public interface IEmailSender
    {
        void SendEmail(Message message);
    }
}
