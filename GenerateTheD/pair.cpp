#include "pair.h"
// This linked list class is used to trace the LCS candidates
 
Pair::Pair(uint32_t index1, uint32_t index2, shared_ptr<Pair> next = nullptr)
: index1(index1), index2(index2), next(next) {

}
 
shared_ptr<Pair> Pair::Reverse(const shared_ptr<Pair> pairs) {
  shared_ptr<Pair> head = nullptr;
  for (auto next = pairs; next != nullptr; next = next->next)
    head = make_shared<Pair>(next->index1, next->index2, head);
  return head;
}