# Model Activity
# Table activities
class Activity < ActiveRecord::Base
  validates :name, presence: true
  validates :description, presence: true
  validates :hours, presence: true
end
