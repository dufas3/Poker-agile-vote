﻿using PokerFace.Data.Entities;

namespace PokerFace.Data.Common
{
    public interface ICardsRepository
    {
        Task<List<Card>> GetCardsAsync();
        Task<List<Card>> GetActiveCardsAsync(int roomId);
        Task SetActiveCardsAsync(int roomId, List<int> cardIds);
        Task<Card> GetAsync(int cardId);
    }
}
