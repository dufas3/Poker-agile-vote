using System.ComponentModel.DataAnnotations;
using System.Diagnostics.CodeAnalysis;

namespace PokerFace.Data.Entities
{
    public class User 
    {
        [Key]
        public int Id { get; set; }
        public int SessionId { get; set; }
        public string Name { get; set; }
        public string? Password { get; set; }
        public Card? SelectCard { get; set; }
    }
}
